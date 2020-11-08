<template>
    <div class="vue-template vertical-center-edit inner-block">
        <form>
            <br></br>
             <br></br>
              <br></br>
            <h3>Edit Your Profile</h3>
            <br></br>
            <div class="form-group">
                <label>Username</label>
                <input type="text" class="form-control form-control-lg"/>
            </div>

            <div class="form-group">
                <label>Email address</label>
                <input type="email" class="form-control form-control-lg" />
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control form-control-lg" />
            </div>

            <div class="form-group">
                <label>Street</label>
                <input type="address" class="form-control form-control-lg" />
                <label>Postal Code</label>
                <input type="address" class="form-control form-control-lg" />
                <label>City</label>
                <input type="address" class="form-control form-control-lg" />
                <label>Province</label>
                <input type="address" class="form-control form-control-lg" />
                <label>Country</label>
                <input type="address" class="form-control form-control-lg" />
            </div>

            <div class="form-group">
            
                <label>Card Holder Name</label>
                <input type="text" class="form-control form-control-lg"/>
                <label>Credit Card Number</label>
                <input type="text" class="form-control form-control-lg" />
                <label>Expiration Date</label>
                <input type="text" class="form-control form-control-lg" />
                <label>Card Verification Code</label>
                <input type="text" class="form-control form-control-lg" />
            </div>

            <router-link :to="{name: 'Profile'}">
                <button type="submit" class="btn btn-dark btn-lg btn-block">Submit</button>
            </router-link>

        </form>
    </div>
</template>

<script>
    export default {
        data() {
            return {}
        }
    }
</script>
