import { Injectable, NgZone } from '@angular/core';
import { Idea } from '../model/Idea';
import { HttpClient } from '@angular/common/http';
import { Observable, from, Observer } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class IdeaService {

  private API_URL = "http://localhost:8080/ideas";

  constructor(
    private _zone: NgZone,
    private http: HttpClient
  ) { }

  stream(): Observable<Idea> {
    return Observable.create(observer => {
      const source = new EventSource(this.API_URL + "/stream")

      source.onmessage = event => {
        this._zone.run(() => {
          observer.next(event)
        })
      }

      source.onerror = err => {
        this._zone.run(() => {
          observer.error(err)
        })
      }
    })
  }

  get(ideaId: String): Promise<Idea> {
    return this.http.get(this.API_URL + "/" + ideaId).pipe(
      map(idea => idea as Idea)
    ).toPromise()
  }

  getAll(): Promise<Idea[]> {
    return this.http.get(this.API_URL).pipe(
      map((ideaList: any) => ideaList.map(idea => idea as Idea))
    ).toPromise()
  }

  create(title: String): Promise<Response> {
    let data = {
      "title": title
    }
    return this.http.post(this.API_URL, data).pipe(
      map(res => res as Response)
    ).toPromise()
  }

}
