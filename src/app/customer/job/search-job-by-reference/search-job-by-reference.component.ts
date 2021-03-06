import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-job-by-reference',
  templateUrl: './search-job-by-reference.component.html',
  styleUrls: ['./search-job-by-reference.component.scss']
})
export class SearchJobByReferenceComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  searchJobByReferenceInAnnonce(libelle: string) {
    console.log("reference+++", libelle);
    this.router.navigateByUrl('/searchInJob/'+libelle);
}

}
