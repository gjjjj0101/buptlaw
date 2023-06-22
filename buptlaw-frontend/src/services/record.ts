//+-------------------------------------------------------------------------
//
//  记录相关的服务
//
//  File:       record.ts
//
//  Directory:  buptlaw-frontend/src/services
//
//  History:    6月-22-2023   QQK  Created
//
//--------------------------------------------------------------------------
import request                                   from '../services/request';
import { Record, RecordRequest, RecordResponse } from "../types/record";
import { BACKEND_URL }                           from "../consts/urls";

// 批量获取记录（分页）
export const getRecordList = (page?: number, size?: number) => {
  return request.get<RecordResponse>(BACKEND_URL + '/record/records'
    + (page !== undefined ? `?page=${page}` : '')
    + (size !== undefined ? `&size=${size}` : ''))
}

// 添加记录
export const addRecord = (record: RecordRequest) => {
  return request.post<unknown>(BACKEND_URL + '/record/records', record)
}
