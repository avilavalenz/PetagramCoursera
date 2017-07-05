package mx.com.tormex.petagram.petagramcoursera;

import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactoActivity extends AppCompatActivity {

    private String correoRemitente = "javamail.ejemplo@gmail.com"; //Los correos salen de esta cuenta
    private String contrasena = "1A2B3C4D";
    private TextInputLayout etNombre, etCorreo, etComentarios;
    private Button btnEnviar;
    private Session sesion;
    private String correoDestinatario = "avilavalenz@icloud.com"; //Los correos van a llegar a este correo.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        etNombre = (TextInputLayout) findViewById(R.id.etNombre);
        etCorreo = (TextInputLayout) findViewById(R.id.etCorreo);
        etComentarios = (TextInputLayout) findViewById(R.id.etComentarios);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                Properties properties = new Properties();
                properties.put("mail.smtp.host", "smtp.googlemail.com");
                properties.put("mail.smtp.socketFactory.port", "465");
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.port", "465");

                try{
                    sesion = Session.getDefaultInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correoRemitente, contrasena);
                        }
                    });
                    if (sesion!= null) {
                        Message message = new MimeMessage(sesion);
                        message.setFrom(new InternetAddress(correoRemitente));
                        message.setSubject("JavaMail");
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestinatario));
                        message.setContent("<table><tr><td><strong>Nombre:</strong></td><td>" + etNombre.getEditText().getText() + "</td></tr><tr><td><strong>Email:</strong></td><td>" + etCorreo.getEditText().getText() + "</td></tr><tr><td><strong>Comentarios:</strong></td><td>" + etComentarios.getEditText().getText().toString() + "</td></tr></table>", "text/html; charset=utf-8");
                        Transport.send(message);
                        Toast.makeText(ContactoActivity.this, "Mensaje enviado con éxito.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getBaseContext(), ListaMascotasActivity.class));
                    }
                } catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(ContactoActivity.this, "Lo sentimos. Su mensaje no pudo ser enviado. Intentelo más tarde.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}