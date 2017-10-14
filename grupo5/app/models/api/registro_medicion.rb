class Api::RegistroMedicion
  include Mongoid::Document
  include Mongoid::Timestamps

  field :valor, type: Float
  field :tipo, type: String
  field :unidad, type: String
end
