//+-------------------------------------------------------------------------
//
//  案件相关的类型定义
//
//  File:       case.ts
//
//  Directory:  buptlaw-frontend/src/types
//
//  History:    6月-15-2023   QQK  Created
//
//--------------------------------------------------------------------------

// 案件的类型
import { PagingResponse } from "./index";

export interface Case {
  caseId: number;           // 案件id
  userId: number;           // 负责人id
  caseName: string;         // 案件名称
  caseReason: string;       // 案由
  caseDescription: string;  // 案件描述
  caseType: string;         // 案件类型
  caseContent: string;      // 案件内容
  caseStatus: string;       // 案件状态
  openTime: number;         // 最近打开时间
  createTime: number;       // 创建时间
  updateTime: number;       // 更新时间
  deleteTime: number;       // 删除时间
}

export interface CasePagingResponse extends PagingResponse<Case> {}
