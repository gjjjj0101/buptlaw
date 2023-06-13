//+-------------------------------------------------------------------------
//
//  封装 axios 请求
//
//  File:       request.ts
//
//  Directory:  buptlaw-frontend/src/services
//
//  History:    6月-10-2023   QQK  Created
//
//--------------------------------------------------------------------------

import { BACKEND_URL } from "../consts/urls";
import axios           from "axios";

axios.defaults.baseURL = BACKEND_URL;
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
axios.defaults.headers.post['Content-Type'] = 'application/json';

export function get<T>(url: string, params?: any) {
  return new Promise<T>((resolve, reject) => {
    axios.get<T>(url, {params})
      .then(res => resolve(res.data))
      .catch(reject)
  })
}

export function post<T>(url: string, data?: any) {
  return new Promise<T>((resolve, reject) => {
    axios.post<T>(url, data)
      .then(res => resolve(res.data))
      .catch(reject)
  })
}

export default {
  get, post
}
