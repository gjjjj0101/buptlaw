//+-------------------------------------------------------------------------
//
//  案件相关的服务
//
//  File:       case.ts
//
//  Directory:  buptlaw-frontend/src/services
//
//  History:    6月-21-2023   QQK  Created
//
//--------------------------------------------------------------------------
import request         from '../services/request';
import { Case }        from "../types/case";
import { BACKEND_URL } from "../consts/urls";

// 批量获取内部案例（分页）
export const getCaseList = (page?: number, size?: number) => {
  return request.get<Case[]>(BACKEND_URL + '/internal/cases'
    + (page !== undefined ? `?page=${page}` : '')
    + (size !== undefined ? `&size=${size}` : ''))
}
