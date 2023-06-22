//+-------------------------------------------------------------------------
//
//  记录相关的类型定义
//
//  File:       record.ts
//
//  Directory:  buptlaw-frontend/src/types
//
//  History:    6月-22-2023   QQK  Created
//
//--------------------------------------------------------------------------

export interface Record {
  recordId: number;         // 记录ID
  userId: number;           // 用户ID
  title: string;            // 标题
  description: string;      // 描述
  content: string;          // 内容
  type: string;             // 类型
  status: number;           // 状态
  createDateTime: number;   // 创建时间
  updateDateTime: number;   // 更新时间
  deleteDateTime: number;   // 删除时间
}

export interface RecordResponse {
  content: Record[];
}
