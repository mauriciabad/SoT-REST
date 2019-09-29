package fontys.sot.rest.service.resources;

import fontys.sot.rest.service.model.User;
import fontys.sot.rest.service.model.UsersCollection;
import fontys.sot.rest.service.model.ResponseError;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("users")
public class UsersResources {
    /*  - - - - - - - - - -  Data  - - - - - - - - - -  */

    private UsersCollection users = UsersCollection.getInstance();



    /*  - - - - - - - - - -  Endpoints  - - - - - - - - - -  */

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserByQuery() {
        return Response.ok().entity(users.getAll()).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createUser(User user) {
        if (user.getName() == null) return new ResponseError(422, "Missing 'name' parameter").build();

        int user_id = users.add(user);

        return Response.ok().entity(users.get(user_id)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response createUser(@FormParam("name") String name) {
        if (name == null) return new ResponseError(422, "Missing 'name' parameter").build();

        int user_id = users.add(new User(name));

        return Response.ok().entity(users.get(user_id)).build();
    }

    @PUT
    @Path("{user_id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateUser(User user, @PathParam("user_id") int user_id) {
        if (users.exists(user_id)){
            User oldUser = users.get(user_id);

            if (user.getName() != null) oldUser.setName(user.getName());

            users.update(oldUser);

            return Response.ok().entity(users.get(user_id)).build();
        }else{
            return new ResponseError(404, "User with user_id " + user_id + " doesn't exist").build();
        }
    }

    @GET
    @Path("{user_id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserById(@PathParam("user_id") int user_id) {
        if (users.exists(user_id)) {
            return Response.ok().entity(users.get(user_id)).build();
        } else {
            return new ResponseError(404, "User with user_id " + user_id + " doesn't exist").build();
        }
    }

    @DELETE
    @Path("{user_id}")
    public Response deleteUser(@PathParam("user_id") int user_id) {
        users.remove(user_id);
        return Response.noContent().build();
    }
}
