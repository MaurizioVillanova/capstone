export interface AuthResponse {
  token: string,
  user: {
      email: string,
      id: number,

  },
  id: number
  roles: []
}

export interface RegisterRequest {
  email:string,
  password:string,
  nomeCompleto:string,
  username:string
}
export interface LoginRequest {
  username:string,
  password:string
}
