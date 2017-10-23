module Api::RegistroMedicionesHelper
  def self.send_email
    mail = Mail.deliver do
      to 'drummerwilliam@gmail.com'
      from 'drummerwilliam@gmail.com'
      subject 'Ruby Example using X-SMTPAPI header'
      text_part do
        body 'You would put your content here, but I am going to say: Hello world!'
      end
      html_part do
        content_type 'text/html; charset=UTF-8'
        body '<b>Hello world!</b><br>Glad to have you here!'
      end
    end

    puts "Enviado"
  end
end
