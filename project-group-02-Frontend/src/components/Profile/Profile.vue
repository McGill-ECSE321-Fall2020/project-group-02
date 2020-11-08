<template>
    <div class="vue-template vertical-center-profile inner-block">
        <form>
            <h3>Your Profile</h3>

            <div class="form-group">
                <label>Username</label>
                <input type="text" id="username" class="form-control form-control-lg" readonly />
                
            </div>

            <div class="form-group">
                <label>Email address</label>
                <input type="text" id="address" class="form-control form-control-lg" readonly />
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="text" id="password" class="form-control form-control-lg" readonly />
            </div>

            <div class="form-group">
                <label>Address</label>
                <input type="text" id="address" class="form-control form-control-lg" readonly />
            </div>

            <div class="form-group">
                <label>Payment Credentials</label>
                <input type="text" id="payment" class="form-control form-control-lg" readonly />
            </div>

            <router-link :to="{name: 'Catalog'}">
                <button type="submit" class="btn btn-dark btn-lg btn-block">Visit Our Catalog</button>
            </router-link>

            <p class="forgot-password text-right">
                Edit your profile
                <router-link :to="{name: 'Edit'}">here</router-link>
            </p>
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
