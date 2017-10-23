class Api::Actuador
  include Mongoid::Document
  field :estado, type: Boolean
end
