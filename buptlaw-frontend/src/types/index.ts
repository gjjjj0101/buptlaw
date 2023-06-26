//+-------------------------------------------------------------------------
//
//  通用类型定义
//
//  File:       index.ts
//
//  Directory:  buptlaw-frontend/src/types
//
//  History:    6月-26-2023   QQK  Created
//
//--------------------------------------------------------------------------

// 分页响应
export interface PagingResponse<T> {
  content: T[];
  pageable: {
    sort: {
      empty: boolean;
      sorted: boolean;
      unsorted: boolean;
    }
    offset: number;
    pageNumber: number;
    pageSize: number;
    paged: boolean;
    unpaged: boolean;
  }
  last: boolean;
  totalPages: number;
  totalElements: number;
  first: boolean;
  size: number;
  number: number;
  sort: {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
  }
  numberOfElements: number;
  empty: boolean;
}
