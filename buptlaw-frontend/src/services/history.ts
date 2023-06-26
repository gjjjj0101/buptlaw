//+-------------------------------------------------------------------------
//
//  用户操作历史记录相关的服务
//
//  File:       history.ts
//
//  Directory:  buptlaw-frontend/src/services
//
//  History:    6月-24-2023   QQK  Created
//
//--------------------------------------------------------------------------
import request                   from '../services/request';
import { BACKEND_URL }           from "../consts/urls";
import { HistoryPagingResponse } from "../types/history";

// 上传用户操作法律法规的历史记录
export const postLawRecordHistory = (regulationId: string, title: string, action: string) => {
  return request.post(BACKEND_URL + '/history/regulations', {
    regulationId,
    title,
    action
  })
}

// 根据用户获取操作记录的历史记录
export const getRecordHistoryByUser = (page?: number, size?: number) => {
  return request.get<HistoryPagingResponse>(BACKEND_URL + '/history/records'
    + (page !== undefined ? `?page=${page}` : '')
    + (size !== undefined ? `&size=${size}` : ''))
}

// 根据用户获取操作法律法规的历史记录
export const getLawHistoryByUser = (page?: number, size?: number) => {
  return request.get<HistoryPagingResponse>(BACKEND_URL + '/history/regulations'
    + (page !== undefined ? `?page=${page}` : '')
    + (size !== undefined ? `&size=${size}` : ''))
}

// 根据操作类型获取操作记录的历史记录
export const getRecordHistoryByAction = (action?: string, page?: number, size?: number) => {
  return request.get<HistoryPagingResponse>(BACKEND_URL + '/history/records/action'
    + (action !== undefined ? `?action=${action}` : '')
    + (page !== undefined ? `&page=${page}` : '')
    + (size !== undefined ? `&size=${size}` : ''))
}

// 根据操作类型获取操作法律法规的历史记录
export const getLawHistoryByAction = (action?: string, page?: number, size?: number) => {
  return request.get<HistoryPagingResponse>(BACKEND_URL + '/history/regulations/action'
    + (action !== undefined ? `?action=${action}` : '')
    + (page !== undefined ? `&page=${page}` : '')
    + (size !== undefined ? `&size=${size}` : ''))
}
