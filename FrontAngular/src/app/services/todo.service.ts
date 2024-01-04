import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Todos } from '../interface/Todos.interface';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  readonly api: string = 'http://localhost:8080/todo';

  constructor(private http: HttpClient) {}

  getAllTodos(): Observable<Todos[]> {
    return this.http.get<Todos[]>(this.api);
  }

  postTodo(body: Partial<Todos>): Observable<Todos> {
    return this.http.post<Todos>(this.api, body);
  }

  updateTodo(body: Partial<Todos>): Observable<Todos> {
    return this.http.put<Todos>(`${this.api}/${body.id}`, body);
  }
}
