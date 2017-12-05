Api::Actuador.delete_all
Api::Alert.delete_all
Api::Microcontrolador.delete_all
Api::Usuario.delete_all
Api::RegistroMedicion.delete_all
Api::VariableAmbiental.delete_all
Delayed::Job.delete_all

varVariableAmbiental1 = Api::VariableAmbiental.new(tipo:"Temperature", valorMaximo:27.0, valorMinimo:21.5 , unidad:"∞C", variacionDiaria:5.4 , precision:2.0 , frecuencia:60)

varVariableAmbiental2 = Api::VariableAmbiental.new(tipo:"Sound", valorMaximo:85.0, valorMinimo:0.0 , unidad:"dB", variacionDiaria:0.0 , precision:2.0 , frecuencia:120)

varVariableAmbiental3 = Api::VariableAmbiental.new(tipo:"Monoxide", valorMaximo:100.0, valorMinimo:0.0 , unidad:"ppm", variacionDiaria:0.0 , precision:2.0 , frecuencia:60)

varVariableAmbiental4 = Api::VariableAmbiental.new(tipo:"Lux", valorMaximo:2000.0, valorMinimo:100.0 , unidad:"Lux", variacionDiaria:0.0 , precision:2.0 , frecuencia:120)

varUsuario = Api::Usuario.new(nombre:"Alejandro", correo:"a.echeverrir@uniandes.edu.co", contrasena:"password1234" )

varVariableAmbiental1.save
varVariableAmbiental2.save
varVariableAmbiental3.save
varVariableAmbiental4.save

i = 0
while i < 10 do
  j = 0
  currentActuador = Api::Actuador.new(estado:false)
  while(j < 10) do
    currentMicrocontrolador = Api::Microcontrolador.new(nivel: i ,area: j)
    currentMicrocontrolador.save
    j+= 1
  end
  currentActuador.microcontrolador = currentMicrocontrolador
  currentActuador.variable_ambiental = Api::VariableAmbiental.all.sample
  currentActuador.save
  i+= 1
end

i = 0
while i < 10 do
  currentAlerta = Api::Alert.new(tipo: rand(0...2), mensaje:"alerta: valor recibido=1 valor minimo=2.0")
  currentAlerta.microcontrolador = Api::Microcontrolador.all.sample
  currentAlerta.save
  i += 1
end

varUsuario.save

i = 0
while i < 50 do
  j = 0
  while(j < 50) do
    currentRegistro = Api::RegistroMedicion.new(valor: rand(1...30))
    currentRegistro.variable_ambiental = Api::VariableAmbiental.all.sample
    currentRegistro.microcontrolador =  Api::Microcontrolador.all.sample
    currentRegistro.save
    j+= 1
  end
  i+= 1
end
