export class PagedResponseModel<T> {
   constructor(public content: T[],
               public pageNumber: number,
               public elementCount: number,
               public totalPages: number,
               public totalElements: number) {}
}