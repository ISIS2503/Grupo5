class Api::Alert
  include Mongoid::Document
  field :fecha, type: Date
  field :tipo, type: Integer
  field :mensaje, type: String
end
