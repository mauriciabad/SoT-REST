package resources;

import model.User;
import collections.UsersCollection;
import utilities.ResponseCustom;
import utilities.ResponseError;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
public class UsersResources {
    /*  - - - - - - - - - -  Data  - - - - - - - - - -  */

    private UsersCollection users = UsersCollection.getInstance();



    /*  - - - - - - - - - -  Endpoints  - - - - - - - - - -  */

    @OPTIONS
    public Response optionsUser() { return ResponseCustom.build(200,"GET, POST, OPTIONS"); }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserByQuery() {
        return ResponseCustom.build(200, users.getAll(), users.total());
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createUser(User user) {
        if (user.getName() == null) return new ResponseError(422, "Missing 'name' parameter").build();

        int userId = users.add(user);

        return ResponseCustom.build(200, users.get(userId));
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response createUser(@FormParam("name") String name) {
        if (name == null) return new ResponseError(422, "Missing 'name' parameter").build();

        int userId = users.add(new User(name));

        return ResponseCustom.build(200, users.get(userId));
    }

    @OPTIONS
    @Path("{userId}")
    public Response optionsUserId() { return ResponseCustom.build(200,"GET, PUT, POST, DELETE, OPTIONS"); }

    @PUT
    @Path("{userId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateUser(User user, @PathParam("userId") int userId) {
        if (users.exists(userId)){
            User oldUser = users.get(userId);

            if (user.getName() != null) oldUser.setName(user.getName());

            users.update(oldUser);

            return ResponseCustom.build(200, users.get(userId));
        }else{
            return new ResponseError(404, "User with userId " + userId + " doesn't exist").build();
        }
    }

    @GET
    @Path("{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserById(@PathParam("userId") int userId) {
        if (users.exists(userId)) {
            return ResponseCustom.build(200, users.get(userId));
        } else {
            return new ResponseError(404, "User with userId " + userId + " doesn't exist").build();
        }
    }

    @DELETE
    @Path("{userId}")
    public Response deleteUser(@PathParam("userId") int userId) {
        users.remove(userId);
        return ResponseCustom.build();
    }
}
