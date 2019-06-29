import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Task} from '../model/Task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  http: HttpClient; 
  
  taskUrl = 'http://localhost:8080/task-manager/';
  tasksUrl = this.taskUrl + 'tasks';
  
  constructor(http: HttpClient ) {
    this.http = http;
  }

  get(): Observable<Task[]> {
    return this.http.get<Task[]>(this.tasksUrl);
  }

  update(tsk: Task): Observable<Task> {
    return this.http.post<Task>(this.taskUrl,tsk);
  }

  getTasks(): Promise<any> {
    return this.http.get(this.tasksUrl).toPromise().then(value => value);
  }

  getTask(id: string): Observable<Task> {
    console.log('id: ', id);
    return this.http.get<Task>(this.taskUrl + '' + id);
  }

  updateTask(id: string, t: Task): Observable<any> {
    return this.http.post(this.taskUrl + '' + id, t);
  }

  deleteTask(id: string): Observable<any> {
    return this.http.post(this.taskUrl + 'delete' , id);
  }

  addTask(t: Task): Promise<any> {
    return this.http.post(this.taskUrl, t).toPromise().then(value => value);
  }
}
