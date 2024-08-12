import { Anchor, Group } from "@mantine/core";
import classes from "./Footer.module.css";

const links = [
    { link: "https://github.com/pecasdev", label: "Peter" },
    { link: "https://github.com/danielperev", label: "Daniel" },
    { link: "https://github.com/pecasdev/jobwait", label: "Source" },
];

export function Footer() {
    const items = links.map((link) => (
        <Anchor
            key={link.label}
            href={link.link}
            target="_blank"
            className={classes.anchors}
        >
            {link.label}
        </Anchor>
    ));

    return (
        <div className={classes.footer}>
            <div className={classes.inner}>
                <Group className={classes.links}>{items}</Group>
            </div>
        </div>
    );
}
