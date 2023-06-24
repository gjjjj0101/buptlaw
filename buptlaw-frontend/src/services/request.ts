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

import { BACKEND_URL }    from "../consts/urls";
import axios              from "axios";
import { ElNotification } from "element-plus";

// axios.defaults.baseURL = BACKEND_URL;
axios.defaults.headers.common['Authorization'] = localStorage.getItem('token')
axios.defaults.headers.post['Content-Type']    = 'application/json'

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
  // 2xx 范围内的状态码都会触发该函数。
  // 对响应数据做点什么
  return response;
}, function (error) {
  // 超出 2xx 范围的状态码都会触发该函数。
  // 对响应错误做点什么
  ElNotification({
    title: '发生错误',
    message: error.message,
    type: 'error'
  })

  return Promise.reject(error);
});

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

export function put<T>(url: string, data?: any) {
  return new Promise<T>((resolve, reject) => {
    axios.put<T>(url, data)
      .then(res => resolve(res.data))
      .catch(reject)
  })
}

export function del<T>(url: string) {
  return new Promise<T>((resolve, reject) => {
    axios.delete<T>(url)
      .then(res => resolve(res.data))
      .catch(reject)
  })
}

export default {
  get, post, put, del
}
