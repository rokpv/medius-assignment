import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Solution} from '../models/Solution';
import {Problem} from '../models/Problem';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  readonly API_URL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getProblems(): Observable<Problem[]> {
    return this.http.get<Problem[]>(`${this.API_URL}/problems/`);
  }

  getProblemById(id: string): Observable<Problem> {
    return this.http.get<Problem>(`${this.API_URL}/problems/${id}`);
  }

  createProblem(size: number, description: string): Observable<Problem> {
    return this.http.post<Problem>(
      `${this.API_URL}/problems/`,
      {
        size,
        description,
      }
    );
  }

  getSolutions(): Observable<Solution[]> {
    return this.http.get<Solution[]>(`${this.API_URL}/solutions`);
  }

  getSolutionByProblemId(id: string): Observable<Solution> {
    return this.http.get<Solution>(`${this.API_URL}/solutions/problem/${id}`);
  }
}
