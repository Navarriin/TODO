import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Todos } from '../interface/Todos.interface';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  readonly api: string = 'http://localhost:3000/Todos';

  constructor(private http: HttpClient) {}

  getAllTodos(): Observable<Todos[]> {
    return this.http.get<Todos[]>(this.api);
  }

  postTodo(body: Todos): Observable<Todos> {
    return this.http.post<Todos>(this.api, body);
  }

  updateTodo(body: Todos): Observable<Todos> {
    return this.http.put<Todos>(`${this.api}/${body.id}`, body);
  }

  deleteTodo(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
