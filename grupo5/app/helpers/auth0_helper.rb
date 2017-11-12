module Auth0Helper
  private
  # Is the user signed in?
  # @return [Boolean]
  def user_signed_in?
    session[:userinfo].present?
  end

  # Set the @current_user or redirect to public page
  def authenticate_user!
    # Redirect to page that has the login here
    if user_signed_in?
      @current_user = session[:userinfo]
      namespace = 'https://arquisoft201720-wrravelo:auth0:com/app_metadata'

      @current_user[:roles] = session[:userinfo][:extra][:raw_info][namespace][:roles]
    else
      redirect_to home_login_path
    end
  end

  def authorize_user!(role)
    if !@current_user[:roles].include? role
      render :json => { :mssg => 'No tiene privilegios para ver la información' }, status: :unauthorized
    end
  end

  # What's the current_user?
  # @return [Hash]
  def current_user
    @current_user
  end

  # @return the path to the login page
  def login_path
    redirect_to '/'
  end
end
