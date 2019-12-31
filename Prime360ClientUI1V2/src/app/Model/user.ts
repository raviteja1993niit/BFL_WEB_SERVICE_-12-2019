import { UserIdentity } from "./useridentity";

export class User
{
    // public username:string;
    // public password:string;
    // public firstname:string;
    // public lastname:string;


    public userIdentity : UserIdentity = new UserIdentity();
    public  userName:string="";
	
	public  departmentName:string="";
	public  dateOfJoining:Date;
	public  emailID:string;
	public  mobile:string
	public  alternativeMobile:string;
	public  branchCode:string;
	public  userClassification:string;
	public  reportingManager:string;
	public  roleID:string;
	public  password:string;
	public  creationalPassword:string;
	public  maker:string;
	public  checker:string;
	public  active:string="Y";
	public  approvalRejected:string;
	public  suspended:number=0;	
	public  expiryDate:Date;	
	public  accountExpired:number=0;	
    public  accountLocked:number=0;
    
}