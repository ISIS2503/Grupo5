<<<<<<< HEAD
Rails.application.routes.draw do
  namespace :api, defaults: {format: 'json'} do
    resources :registro_mediciones, only: [:create, :index, :show, :destroy]
  end
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html

end
=======
Rails.application.routes.draw do
  namespace :api, defaults: {format: 'json'} do
    resources :registro_mediciones, only: [:create, :index, :show, :destroy]
    resources :microcontrolador, only: [:index]
    resources :variable_ambiental, only: [:index]

  end
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html

end
>>>>>>> 1a60729b7a9d0285f8cdecda9b5c21051de8268f
