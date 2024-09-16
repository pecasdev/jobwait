import { Button } from "@mantine/core";
import { useLinkedIn } from "react-linkedin-login-oauth2";

const client_id = "78c9i5swfp7ii3";
const scope = "openid email";
const redirect_uri = "http://127.0.0.1:3000/auth/callback";

export function LinkedInLoginButton(props: {
    setLogin: (loginState: boolean) => void;
}) {
    const { linkedInLogin } = useLinkedIn({
        clientId: client_id,
        redirectUri: redirect_uri,
        onSuccess: (code) => {
            console.log("success", code); //send to backend
            props.setLogin(true);
        },
        onError: (error) => {
            console.error("error", error);
        },
        scope: scope, //dont need access to profile (can get UUID [sub] with just email)
    });

    return <Button onClick={linkedInLogin}>Login</Button>;
}
