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

        int userId = users.add(user);

        return Response.ok().entity(users.get(userId)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response createUser(@FormParam("name") String name) {
        if (name == null) return new ResponseError(422, "Missing 'name' parameter").build();

        int userId = users.add(new User(name));

        return Response.ok().entity(users.get(userId)).build();
    }

    @PUT
    @Path("{userId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateUser(User user, @PathParam("userId") int userId) {
        if (users.exists(userId)){
            User oldUser = users.get(userId);

            if (user.getName() != null) oldUser.setName(user.getName());

            users.update(oldUser);

            return Response.ok().entity(users.get(userId)).build();
        }else{
            return new ResponseError(404, "User with userId " + userId + " doesn't exist").build();
        }
    }

    @GET
    @Path("{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserById(@PathParam("userId") int userId) {
        if (users.exists(userId)) {
            return Response.ok().entity(users.get(userId)).build();
        } else {
            return new ResponseError(404, "User with userId " + userId + " doesn't exist").build();
        }
    }

    @DELETE
    @Path("{userId}")
    public Response deleteUser(@PathParam("userId") int userId) {
        users.remove(userId);
        return Response.noContent().build();
    }
}
