package com.edu.wzh.web;

import com.edu.wzh.code.Msg;
import com.edu.wzh.entity.AdminEntity;
import com.edu.wzh.entity.AuthEntity;
import com.edu.wzh.entity.RoleEntity;
import com.edu.wzh.pojo.AdminPojo;
import com.edu.wzh.pojo.AuthPojo;
import com.edu.wzh.pojo.RolePojo;
import com.edu.wzh.service.Adminservice;
import com.edu.wzh.service.AuthService;
import com.edu.wzh.service.RoleService;
import com.edu.wzh.utils.ObjectUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/*

@RestController
public class AdminController {
    @Autowired
    private Adminservice  adminservice;
    @RequestMapping("login")
    public Msg adminLogin(AdminEntity adminEntity){
        if(ObjectUtils.checkNull(adminEntity)){
            return Msg.fail();
        }
        if(ObjectUtils.checkNull(adminEntity.getAacount())){
            return Msg.fail();
        }
        if(ObjectUtils.checkNull(adminEntity)){
            return Msg.fail();
        }
        AdminPojo adminPojo =adminservice.login(adminEntity);
        System.out.println(adminPojo);
        if(adminPojo==null){
            return Msg.fail();
        }
        return Msg.success();
    }
}
*/

@Controller
@SessionAttributes({"admin","paths","roles"})
public class AdminController {
    @RequestMapping("/*/welcome")
    public  String welcome(){
        return  "forward:/pages/welcome.jsp";
    }
    @RequestMapping("welcome")
    public  String welcom(){
        return  "forward:/pages/welcome.jsp";
    }
    @Autowired
    private Adminservice  adminservice;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;
    @RequestMapping("login")
    public String adminLogin(AdminEntity adminEntity,Model model){
        if(ObjectUtils.checkNull(adminEntity)){
            model.addAttribute("error","系统异常");
            return "forward:index.jsp";
        }
        if(ObjectUtils.checkNull(adminEntity.getAacount())){
            model.addAttribute("error","用户名不能为空");
            return "forward:index.jsp";
        }
        if(ObjectUtils.checkNull(adminEntity.getApass())){
            model.addAttribute("error","密码不能为空");
            return "forward:index.jsp";
        }
        AdminPojo adminPojo =adminservice.selectAuthByAdmin(adminEntity);

        if(adminPojo==null){
            model.addAttribute("error","用户名或者密码错误");
            return "forward:index.jsp";
        }
        Set<String> paths=getAuthPath(adminPojo);
        StringBuilder sb=new StringBuilder();
        for (String s : paths) {
            sb.append(s);
        }
        System.out.println(sb);
        model.addAttribute("paths",sb.toString());
        model.addAttribute("admin",adminPojo);
        return "/pages/Adminmain.jsp";
    }

    public Set<String> getAuthPath(AdminPojo pojo){
        Iterator <RolePojo> iterator =pojo.getRolePojo().iterator();
        RolePojo rolePojo=null;
        Iterator<AuthPojo> iterator2=null;
        AuthPojo authPojo;
        Set<String> set=new HashSet<>();
        while (iterator.hasNext()){
            rolePojo=iterator.next();
            iterator2=rolePojo.getAuthPojos().iterator();
            while (iterator2.hasNext()){
                authPojo=iterator2.next();
                set.add(authPojo.getAupath());
            }
        }
        return set;
    }

    @RequestMapping("adminlist")
    public  ModelAndView  adminList(AdminEntity adminEntity){
        ModelAndView mv=new ModelAndView("/pages/admin-list.jsp");
        List<AdminPojo> adminPojos=adminservice.conditionsSelectAdmin(adminEntity);


        PageInfo <AdminPojo> pageInfo=new PageInfo<>(adminPojos,4);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("adminEntity",adminEntity);
        return mv;
    }

    @RequestMapping("jumpAdminAdd")
    public  String  adminAdd(Model model,RoleEntity roleEntity){

        List<RolePojo> roles= roleService.getRoles(roleEntity);

        model.addAttribute("roles",roles);

        return "/pages/admin-add.jsp";
    }


    @RequestMapping("adminEdit")
    public  String  AdminEdit(Model model, AdminEntity adminEntity, RoleEntity roleEntity){
        AdminPojo adminPojo=adminservice.selectAuthByAdmin(adminEntity);

        List<RolePojo> roles= roleService.getRoles(roleEntity);
        List<Integer>rids=new ArrayList<>();
        for (RolePojo o : adminPojo.getRolePojo() ){
            rids.add(o.getRid());
        }
        model.addAttribute("rids",rids);
        model.addAttribute("roles",roles);
        model.addAttribute("adminPojo",adminPojo);

        return  "/pages/admin-edit.jsp";
    }

    @RequestMapping("updateEdit")
    public  void   updateEdit(Model model,AdminEntity adminEntity,int [] rids){
        adminservice.updateEdit(adminEntity,rids);

       /* model.addAttribute("adminPojo",adminPojo);*/
       /* return  "adminlist";*/
    }

/*
* 添加用户及其权限
* */
    @RequestMapping("insertAdmin")

    public  String updateAdmin(int [] rids ,AdminEntity adminEntity ){

        adminservice.insertAdmin(rids,adminEntity);
        return "/pages/admin-list.jsp";
    }
    @RequestMapping("delectAdmin")
    @ResponseBody
    public  Msg delectAdmin(int [] data){
        if(data!=null){
            if(adminservice.delectAdmin(data)){
                return Msg.success();
            }
        }
        return Msg.fail();
    }
    /*
    * 跳转role 管理
    * */
    @RequestMapping("adminRole")
   public  String jumpAdminRole(RoleEntity roleEntity,Model model){
        List<RolePojo> roles= roleService.getRoles(roleEntity);
        PageInfo<RolePojo> pageInfo=new PageInfo<>(roles,4);
        model.addAttribute("pageInfos",pageInfo);
        return  "pages/admin-role.jsp";
   }

   /*
   * 跳转添加角色页面 ， 查找出所有权限列表 回显当前角色权限
   * */
   @RequestMapping("jumpAddRole")
   public  String jumpAddRole(AuthEntity authEntity,Model model,RoleEntity roleEntity){
       List<AuthPojo> authPojos=authService.getroles(authEntity);
        RolePojo role=roleService.getRoleByRid(roleEntity.getRid());
        model.addAttribute("ros",role);


       List<AuthPojo> auths=authService.getAuthsByRid(roleEntity.getRid());
       List<Integer> authes=new ArrayList<>();
       for (AuthPojo auth : auths) {
           authes.add(auth.getAuid());
       }
       model.addAttribute("authides",authes);
       model.addAttribute("auths",authPojos);
       return "/pages/role-add.jsp";
   }
   /*
   * 添加角色
   * */
   @RequestMapping("addRole")
   @ResponseBody
   public  Msg   addRole(RoleEntity roleEntity,int [] auids){
       roleService.addRole(roleEntity,auids);
        /*return "adminRole";*/
       return Msg.success();
   }

}