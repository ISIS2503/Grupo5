class Api::RegistroMedicion
  include Mongoid::Document
  include Mongoid::Timestamps

  field :valor, type: Float

  validates :valor, presence: true

  belongs_to :variable_ambiental, :class_name => 'Api::VariableAmbiental'
  belongs_to :microcontroladores, :class_name => 'Api::Microcontrolador'
end
