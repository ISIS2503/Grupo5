Rails.application.routes.draw do
  namespace :api, defaults: {format: 'json'} do
    resources :registro_mediciones, only: [:create, :index, :show, :destroy]
    resources :microcontrolador, only: [:index]
    resources :variable_ambiental, only: [:index]
    resources :alert, only: [:index]
    resources :usuario, only: [:index]
    resources :actuador, only: [:index]
  end
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html

end
