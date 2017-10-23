class Api::Microcontrolador
  include Mongoid::Document
  field :nivel, type: Integer
  field :area, type: Integer
end
