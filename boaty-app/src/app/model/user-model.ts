export class UserModel {

    constructor(public id: number,
                public username: string,
                public firstName: string,
                public lastName: string,
                public balance: number,
                public role: string) { }
}
