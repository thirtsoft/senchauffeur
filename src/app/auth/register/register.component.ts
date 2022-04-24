import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './../security/auth.service';
import { Register } from './../security/register';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registrationForm: FormGroup;
  user = new Register('','','','',[]);
  submitted = false;
  isRegistered = false;
  isSignUpFailed = false;
  errorMessage = '';
  roles: any = [
    { name: 'User', id:1, selected: true },
    { name: 'Vendeur', id:2, selected: false },
    { name: 'Admin', id:3, selected: false },
  ];
  selectedRoles: string[];

  constructor(private authService: AuthService,
              private toastr: ToastrService,
              private router : Router
  ){}

  ngOnInit(): void {
    this.registrationForm = new FormGroup({
      name: new FormControl(null, [Validators.required]),
      username: new FormControl(null, [Validators.required]),
      email: new FormControl(null, [
        Validators.required, 
        Validators.pattern('^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$')
      ]),
      password: new FormControl(null, [
        Validators.required,
        Validators.minLength(6)
      ]),
      roleSelection: this.createRoles(this.roles),
    });
  }
  createRoles(rolesList): FormArray {
    const arr = rolesList.map(role => {
      return new FormControl(role.selected)
    });
    console.log("CreateRole:" +arr);
    return new FormArray(arr);
  }

  onSubmit() {
    this.submitted = true;
    this.user.name = this.registrationForm.value.name;
    this.user.username = this.registrationForm.value.username;
    this.user.email = this.registrationForm.value.email;
    this.user.password = this.registrationForm.value.password;
    console.log("SelectedRole: " +this.getSelectedRoles());
    this.user.roles = this.getSelectedRoles();
    this.registerUser();
  }

  registerUser() {
    console.log(this.user);
    this.authService.signUp(this.user)
    .subscribe(response=> {
      console.log(response);
      this.isRegistered = true;
      this.isSignUpFailed = false;
      this.toastr.success('avec succès','Vote compte est crée', {
        timeOut: 1500,
        positionClass: 'toast-top-right',
        });
        this.router.navigateByUrl("auth/success-register");
    },
    error => {
      this.errorMessage = error.error.message;
      this.toastr.error("Veuillez remplir tous les champs");
      this.isSignUpFailed = true;
    }
    );

  }

  getSelectedRoles():string[]  {
    this.selectedRoles = this.registrationForm.value.roleSelection.map((selected:any, i) => {
      console.log("IsSelected: " +selected);
      if(selected){
        return this.roles[i].name;
      }else {
        return '';
      }
    });
    return this.selectedRoles.filter(function (element) {
      if (element !== '') {
        console.log("ElementReturn: " +element);
        return element;
      }
    });
  }


}
