class Api::Microcontrolador
  include Mongoid::Document
  field :nivel, type: Integer
  field :area, type: Integer

  has_many :alerts, :class_name => 'Api::Alert'
end
