class Api::Alert
  include Mongoid::Document
  include Mongoid::Timestamps
  
  field :tipo, type: Integer
  field :mensaje, type: String

  belongs_to :microcontrolador, :class_name => 'Api::Microcontrolador'
end
