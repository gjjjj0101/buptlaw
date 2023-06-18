//+-------------------------------------------------------------------------
//
//  法律法规相关的类型定义
//
//  File:       law.ts
//
//  Directory:  buptlaw-frontend/src/types
//
//  History:    6月-15-2023   QQK  Created
//
//--------------------------------------------------------------------------

export interface Law {
  id: string;

}

export interface LawSearchBody {
  content?: string;       // 搜索内容
  title?: boolean;        // 是否在标题中进行搜索
  html?: boolean;         // 是否在正文中进行搜索
  isExactly?: boolean;    // 是否精确匹配
  office?: string[];      // 制定机关
  status?: string[];      // 时效性
  level?: string[];       // 法律效力位阶
  lawClass?: string[];    // 法律分类
  officeClass?: string[]; // 机构分类
  publishStart?: number;  // 发布时间范围左区间时间戳
  publishEnd?: number;    // 发布时间范围右区间时间戳
  expiryStart?: number;   // 失效时间范围左区间时间戳
  expiryEnd?: number;     // 失效时间范围右区间时间戳
}
