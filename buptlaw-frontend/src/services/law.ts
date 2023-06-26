//+-------------------------------------------------------------------------
//
//  法律相关的请求
//
//  File:       law.ts
//
//  Directory:  buptlaw-frontend/src/services
//
//  History:    6月-15-2023   QQK  Created
//
//--------------------------------------------------------------------------
import request                                                                 from '../services/request';
import { LawSearchBody, LawMixedSearchResponse, LawNormalSearchResponse, Law } from "../types/law";
import { LAW_BACKEND_URL }                                                     from "../consts/urls";

// 混合查询法律法规
export const getLawByMixedSearch = (key?: LawSearchBody, page?: number, size?: number) => {
  return request.post<LawMixedSearchResponse>(LAW_BACKEND_URL + '/regulation/mixed'
    + (page !== undefined ? `?page=${page}` : '')
    + (size !== undefined ? `&size=${size}` : '')
    , key)
}

// TODO: 将'/regulation/level'替换
// 根据法规类型获取法规
export const getLawByLawClass = (lawClass: string, page?: number, size?: number) => {
  return request.get<LawNormalSearchResponse>(LAW_BACKEND_URL + '/regulation/level'
    + `?lawClass=${lawClass}`
    + (page !== undefined ? `&page=${page}` : '')
    + (size !== undefined ? `&size=${size}` : ''))
}

// 根据部门获得法律法规
export const getLawByOffice = (office: string, page?: number, size?: number) => {
  return request.get<LawNormalSearchResponse>(LAW_BACKEND_URL + '/regulation/office'
    + `?office=${office}`
    + (page !== undefined ? `&page=${page}` : '')
    + (size !== undefined ? `&size=${size}` : ''))
}

// 根据 id 获得法律法规
export const getLawById = (id: string) => {
  return request.get<Law>(LAW_BACKEND_URL + '/regulation/id'
    + `?id=${id}`)
}
