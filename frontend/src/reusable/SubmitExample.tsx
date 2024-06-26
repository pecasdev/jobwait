import { useMutation } from "@apollo/client";
import { ADD_USER } from "../documents/UserDocument";

export default function SubmitExample() {
    let input: HTMLInputElement;

    const [addUser, { data }] = useMutation(ADD_USER);

    return (
        <div>
            <form
                onSubmit={(e) => {
                    e.preventDefault();
                    addUser({ variables: { name: input.value } });
                    input.value = "";
                }}
            >
                <label>
                    Name:
                    <input
                        type="text"
                        ref={(node) => {
                            if (node) input = node;
                        }}
                    />
                </label>
                <button type="submit">Add User</button>
            </form>
            <label>{data?.addUser?.userID}</label>
        </div>
    );
}
