package com.edu.sale.timeTask;

import com.edu.sale.service.SaleService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class TimeTasks extends TimerTask {
    private  String oid;
    private  Timer  timer;
    HttpSession session;
    public TimeTasks() {
    }

    public TimeTasks(String oid, Timer timer, HttpSession session) {
        this.oid = oid;
        this.timer = timer;
        this.session=session;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @Override
    public void run() {

        WebApplicationContext wat = ContextLoader.getCurrentWebApplicationContext();
        SaleService service = wat.getBean(SaleService.class);

        boolean b=service.updateOrder(oid);
        if(b){
            timer.cancel();
            session.getServletContext().removeAttribute("timer");
        }
    }
}
