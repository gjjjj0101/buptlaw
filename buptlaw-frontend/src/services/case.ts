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
import request                      from '../services/request';
import { CasePagingResponse } from "../types/case";
import { BACKEND_URL }              from "../consts/urls";

// 批量获取内部案例（分页）
export const getCaseList = (page?: number, size?: number) => {
  return request.get<CasePagingResponse>(BACKEND_URL + '/internal/cases'
    + (page !== undefined ? `?page=${page}` : '')
    + (size !== undefined ? `&size=${size}` : ''))
}

// 获取各种案由的案例数量
export const getCaseReasonNums = () => {
  return request.get<{ caseReason: string, num: number }[]>(BACKEND_URL + '/internal/cases/reason/nums')
}

// 获取各种案件类型的案件数量
export const getCaseTypeNums = () => {
  return request.get<{ caseType: string, num: number }[]>(BACKEND_URL + '/internal/cases/type/nums')
}

// 获取各种案件状态的数量
export const getCaseStatusNums = () => {
  return request.get<{ status: string, num: number }[]>(BACKEND_URL + '/internal/cases/status/nums')
}
