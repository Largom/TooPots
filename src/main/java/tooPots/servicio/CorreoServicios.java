package tooPots.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.SpanShapeRenderer;
import tooPots.dao.MonitorDao;
import tooPots.modelo.Monitor;

@Service
public class CorreoServicios {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MonitorDao monitorDao;


    public void sendMail(String from, String to, String subject, String body){

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        javaMailSender.send(mailMessage);



    }

    public Monitor getDatosEnvio(int id_monitor){
         return monitorDao.consultaMonitor(id_monitor);
    }

}