import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-search-in-candidates',
  templateUrl: './search-in-candidates.component.html',
  styleUrls: ['./search-in-candidates.component.scss']
})
export class SearchInCandidatesComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  searchArticleInCandidate(disponibility: string) {
      console.log("disponibility+++", disponibility);
      this.router.navigateByUrl('/searchInCandidates/'+disponibility);
  }

}
