export default function debounceFunction(
    func: (...args: any[]) => void,
    delayMS: number,
): (...args: any[]) => void {
    let timeout: NodeJS.Timeout;
    return (...args: any[]) => {
        clearTimeout(timeout);
        timeout = setTimeout(() => func(...args), delayMS);
    };
}
