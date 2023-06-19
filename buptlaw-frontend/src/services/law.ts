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
import request                              from '../services/request';
import { LawSearchBody, LawSearchResponse } from "../types/law";
import { LAW_BACKEND_URL }                  from "../consts/urls";

export const getLawBySearch = (key?: LawSearchBody, page?: number, size?: number) => {
  return request.post<LawSearchResponse>(LAW_BACKEND_URL + '/regulation/mixed'
    + (page !== undefined ? `?page=${page}` : '')
    + (size !== undefined ? `&size=${size}` : '')
    , key);
}
