package com.gymstatsapirest.service;

import com.gymstatsapirest.model.Suscripcione;
import com.gymstatsapirest.model.Usuario;
import com.gymstatsapirest.repository.SuscripcionesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EmailService {
    private Logger logger=LoggerFactory.getLogger(EmailService.class);
    @Autowired private JavaMailSender javaMailSender;
    @Autowired private SuscripcionesRepository suscripcionesRepository;
    @Autowired private Utils utils;
    public int cancelarSuscripcionExpiradas(){
        return suscripcionesRepository.actualizarSuscripcionesExpiradas(new Date(System.currentTimeMillis()),utils.getEstadoSuscripcionExpirada());
    }
    public int enviarEmailSuscripcionesPorExpirar(){
        Date date=new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -10);
        List<String> suscripcione=suscripcionesRepository.darSuscripcionesPorExpirar(utils.getTarifaDiaria(),utils.getEstadoSuscripcionVigente(),
                calendar.getTime());
        suscripcione.forEach((email)->{
            try {
                sendEmail(email);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
        return suscripcione.size();
    }
    private void sendEmail(String email) throws MailException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        // Enable the multipart flag!
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setText("<img src='cid:id101'/> " +
                "       <table>\n" +
                "           <tbody><tr>\n" +
                "               <td align=\"right\">\n" +
                "                   <img style=\"display:block\" class=\"CToWUd\" vspace=\"6\" hspace=\"6\" height=\"35\" align=\"right\">\n" +
                "               </td>\n" +
                "           </tr>\n" +
                "           <tr>\n" +
                "               <td>\n" +
                "                   <p style=\"margin-bottom:6px;margin-top:0px;border-top:7px solid #ffc000;text-align:center;font-size:1px\">&nbsp;</p>\n" +
                "               </td>\n" +
                "           </tr>\n" +
                "        </tbody></table>\n" +
                "        <br>\n" +
                "        <table>\n" +
                "            <tbody><tr>\n" +
                "               <td>\n" +
                "                   Gymstats alert\n" +
                "               </td>\n" +
                "               <td align=\"right\">\n" +
                "                   05/29/2019\n" +
                "               </td>\n" +
                "           </tr>\n" +
                "         </tbody></table>\n" +
                "         <br>\n" +
                "         <br>\n" +
                "         <table>\n" +
                "           <tbody><tr>\n" +
                "               <td>Estimado cliente de Gymstats, </td>\n" +
                "           </tr>\n" +
                "        </tbody></table>\n" +
                "        <br>\n" +
                "        <table>\n" +
                "           <tbody><tr>\n" +
                "               <td>\n" +
                "                Tu suscripción esta por expirar.\n" +
                "               </td>\n" +
                "           </tr>\n" +
                "       </tbody></table> \n" +
                "       <br>\n" +
                "       <table style=\"border-collapse:collapse;max-width:800px\">\n" +
                "           <tbody><tr bgcolor=\"#CCCCCC\" align=\"left\">\n" +
                "               <td class=\"m_-4014588647578919806tableCell\">\n" +
                "                   <font color=\"#000000\"></font>\n" +
                "               </td>\n" +
                "               <td class=\"m_-4014588647578919806tableCell\">\n" +
                "                   <font color=\"#000000\"></font>\n" +
                "               </td>\n" +
                "           </tr>\n" +
                "           \n" +
                "           <tr>\n" +
                "               <td class=\"m_-4014588647578919806tableCell\">\n" +
                "                   \n" +
                "               </td>\n" +
                "               <td class=\"m_-4014588647578919806tableCell\">\n" +
                "                   \n" +
                "               </td>\n" +
                "           </tr>  \n" +
                "       </tbody></table>\n" +
                "       <br>\n" +
                "       <table>\n" +
                "           <tbody><tr>\n" +
                "               <td>\n" +
                "            <a href=\"http://localhost:3000/\">Opten mas información acerca de nuestras rutina aquí</a>.\n" +
                "               </td>\n" +
                "           </tr>\n" +
                "       </tbody></table> \n" +
                "       <br>\n" +
                "        <table>\n" +
                "           <tbody><tr>\n" +
                "               <td>\n" +
                "                <em>This alert is provided by <a href=\"https://compila-pero-no-funciona\">Compila pero no hace nada</a>.</em>\n" +
                "               </td>\n" +
                "           </tr>\n" +
                "       </tbody></table> \n" +
                "       <br>\n" +
                "       <br>\n" +
                "       <br>\n" +
                "       <br>\n" +
                "       <br>\n" +
                "       <br>\n" +
                "       <br>\n" +
                "       <p class=\"m_-4014588647578919806footer\">\n" +
                "           \"\"</a>\n" +
                "  </p>\n" +
                "  <p class=\"m_-4014588647578919806footer\">\n" +
                "    \"\"\n" +
                "  </p>\n" +
                "    <img src=\"https://ci3.googleusercontent.com/proxy/sTXa42pI3h14lCeMOSo9rfh31OL0bzp0luhiIzFtgLLqtd-sokF97mJDTwVI7PKam2-E5XsKEHTi5liqM0VztFouKJTU14p3zFeohgF0lJNsnZY9HJzWBu2q_Ib31EDIxHeZ3KulNPQjmOH8Rt6ot8IKrikuvtBv0wQxNxKb8KKgVgI5AVM7nGDuu_YX6EqhckSIRefmJwoamhwfMbt5cLrpVuuWdYzxpokQslpnO5i1Rh4pWUr-kNXEczOPZhFynt43PggmKsxH6l7RHl8ia29_KKpDX7FoGmfTpo-biyzmFuqmQ1e7NSf9FH66-uaHfGbZmEMuNl8FPlRehwwp4rR8pMBallDm_bV_-RXG4Tejygd5qQuUrb1VL6nmnlSIIv_ho2jiJZ9egK67SixK1g-U_W6koOih=s0-d-e1-ft#https://www.amazon.com/gp/r.html?C=1970XDYI1EEUZ&amp;K=1K3PUJMJHHQQI&amp;M=urn:rtn:msg:201905290612243c4bbb00469a47e5958226371f70p0na&amp;R=2UHM8BIM34NIZ&amp;T=E&amp;U=https%3A%2F%2Fimages-na.ssl-images-amazon.com%2Fimages%2FG%2F01%2Fnav%2Ftransp.gif&amp;H=VONYOZ0LR5YS60WIDKGORRXAWKSA&amp;ref_=pe_3493790_260402510_open\" class=\"CToWUd\" width=\"1\" height=\"1\">\n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    ",true);
        helper.setSubject("Gymstats: Alerta suscripción por expirar");
        ClassPathResource file = new ClassPathResource("apple.png");
        helper.addInline("id101", file);
        javaMailSender.send(message);
    }
}
