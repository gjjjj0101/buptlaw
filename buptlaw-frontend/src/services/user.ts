//+-------------------------------------------------------------------------
//
//  用户相关的请求
//
//  File:       user.ts
//
//  Directory:  buptlaw-frontend/src/services
//
//  History:    6月-15-2023   QQK  Created
//
//--------------------------------------------------------------------------
import request                              from '../services/request';
import { UserLoginBody, UserLoginResponse } from "../types/user";
import { store }                            from "../store/index";
import { BACKEND_URL }                      from "../consts/urls";

// 用户登录
export const login = async (body: UserLoginBody) => {
  return request.post<UserLoginResponse>(BACKEND_URL + '/auth/login', body);
}

// 用户登出
export const logout = () => {
  store.commit('setState', { token: null, username: null })
  localStorage.removeItem('token');
  localStorage.removeItem('username');
}
