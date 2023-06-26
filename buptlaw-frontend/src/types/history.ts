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

import { PagingResponse } from "./index";

export interface History {
  id: number;
  userId: number;
  regulationId: string;
  title: string;
  action: string;
  createTime: number;
}

export interface HistoryPagingResponse extends PagingResponse<History> {}
