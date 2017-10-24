module Api::RegistroMedicionesHelper
  def self.send_email
    mail = Mail.deliver do
      to 'wr.ravelo@uniandes.edu.co'
      from 'n.aguilar@uniandes.edu.co'
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

  def self.verify(medicion, promedio)
    #Alerta fuera de linea
    lastTimestamp = Api::RegistroMedicion.last.created_at if Api::RegistroMedicion.last != nil
    currentTimestamp = Time.now.utc

    # Se manda alerta si esta fuera del tiempo estipulado
    if lastTimestamp && currentTimestamp > (lastTimestamp + 5*medicion.variable_ambiental.frecuencia) #segundos
      puts "Alerta Fuera de linea"
      Api::RegistroMedicionesHelper.delay.send_email
      #TODO Se genera alerta
      return false
    end

    #Alerta fuera rango
    limiteInferior = medicion.variable_ambiental.valorMinimo - medicion.variable_ambiental.variacionDiaria
    limiteSuperior = medicion.variable_ambiental.valorMaximo - medicion.variable_ambiental.variacionDiaria

    # Si esta fuera de los rangos se prenden los actuadores
    if medicion.valor < limiteInferior || medicion.valor > limiteSuperior
      #TODO Se genera alerta
      puts "Alerta Fuera de rango"

      if medicion.microcontrolador.actuadores.where(estado: 0).count
        medicion.microcontrolador.actuadores.each do |actuador|
          actuador.update_attributes(estado: 1)
        end

        puts "Se prendieron los actuadores"
      end
      return false
    else
      # Si se esta en el rango y hay actuadores prendidos hay que apagarlos
      if  medicion.microcontrolador.actuadores.where(estado: 1).count
        medicion.microcontrolador.actuadores.each do |actuador|
          actuador.update_attributes(estado: 0)
        end

        puts "Se apagaron los actuadores"
      end
    end

    return true
  end

  def verify_actuador_off
    puts "Buenas posi"
  end
end
