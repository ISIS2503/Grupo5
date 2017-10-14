class RegistroMedicion
  include Mongoid::Document
  field :valor, type: Float
  field :tipo, type: String
  field :unidad, type: String
end
