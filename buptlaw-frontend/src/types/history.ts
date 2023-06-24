//+-------------------------------------------------------------------------
//
//  用户操作历史相关的类型定义
//
//  File:       history.ts
//
//  Directory:  buptlaw-frontend/src/types
//
//  History:    6月-24-2023   QQK  Created
//
//--------------------------------------------------------------------------

export interface History {
  id: number;
  userId: number;
  regulationId: string;
  title: string;
  action: string;
  createTime: number;
}

export interface HistoryPagingResponse {
  content: History[];
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
