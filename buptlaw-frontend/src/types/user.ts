//+-------------------------------------------------------------------------
//
//  用户相关的类型定义
//
//  File:       user.ts
//
//  Directory:  buptlaw-frontend/src/types
//
//  History:    6月-16-2023   QQK  Created
//
//--------------------------------------------------------------------------

// 用户登录请求体
export interface UserLoginBody {
  username: string;
  password: string;
}

// 用户登录响应体
export interface UserLoginResponse {
  username: string;
  token: string;
}
